from functools import cached_property
from http.server import BaseHTTPRequestHandler, HTTPServer
from tempfile import TemporaryFile
from urllib.parse import parse_qsl, urlparse
from scipy.io.wavfile import write as write_wav

from bark import generate_audio, preload_models, SAMPLE_RATE
import torch
import os
from threading import Thread

hostName = "0.0.0.0"
serverPort = 80

print("Setting up torch hotfix...")

# Save the original torch.load function
_original_torch_load = torch.load

# Define a new function that forces weights_only=False
def custom_torch_load(*args, **kwargs):
    if "weights_only" not in kwargs:
        kwargs["weights_only"] = False
    return _original_torch_load(*args, **kwargs)

# Override torch.load globally
torch.load = custom_torch_load

print("Setting env variables...")

os.environ["SUNO_OFFLOAD_CPU"] = "True"
os.environ["SUNO_USE_SMALL_MODELS"] = "True"

print("Done :)")

class MyServer(BaseHTTPRequestHandler):
    @cached_property
    def url(self):
        return urlparse(self.path)

    @cached_property
    def query_data(self):
        return dict(parse_qsl(self.url.query))

    @cached_property
    def post_data(self):
        content_length = int(self.headers.get("Content-Length", 0))
        return self.rfile.read(content_length)

    @cached_property
    def form_data(self):
        return dict(parse_qsl(self.post_data.decode("utf-8")))

    def do_POST(self):
        print("Received request")

        text_prompt = self.post_data.decode("utf-8")

        if len(text_prompt) < 5:
            self.send_response(400)
            self.end_headers()
            return

        self.send_response(200)
        self.send_header("Content-type", "audio/wav")
        self.end_headers()

        print("Generating audio...")
        audio_array = generate_audio(text_prompt)

        print("Audio generated; length: " + str(len(audio_array)))

        tempfile = TemporaryFile()

        write_wav(tempfile, SAMPLE_RATE, audio_array)

        self.wfile.write(tempfile.read())

def async_preload_models():
    print("Preloading models")
    try:
        preload_models()
        print("Models preloaded successfully, starting server")
    except FutureWarning:
        print("Models preloading failed, continuing to start server")

if __name__ == "__main__":
    t = Thread(target=async_preload_models, daemon=True)
    t.start()

    webServer = HTTPServer((hostName, serverPort), MyServer)
    print("Server started http://%s:%s" % (hostName, serverPort))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("Server stopped.")