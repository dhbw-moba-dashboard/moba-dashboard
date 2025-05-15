//import custom ts function
import { setConsoleMessage } from "../tools/messages";

//define and export async function to play sounds
export async function playSound(passedTrainName: string): Promise<void> {
    try {
        //define url to for endpoint call
        const PLAY_SOUND_ENDPOINT: string = 
            `${process.env.REACT_APP_TTS_MODEL_URL}`;

        //fetch audio file
        const audioResponse = await fetch(PLAY_SOUND_ENDPOINT, {
            method: "POST",
            headers: { 'Content-Type': 'text/plain; charset=utf-8' },
            body: `Einfahrt des Zuges ${passedTrainName ? passedTrainName : "Crossrail"}.`
        });
        const arrayBuffer = await audioResponse.arrayBuffer();

        //create audio context to decode
        const audioContext= new (window.AudioContext || (window as any).webkitAudioContext)();
        const audioBuffer= await audioContext.decodeAudioData(arrayBuffer);

        //create buffer source mode
        const source = audioContext.createBufferSource();
        source.buffer = audioBuffer;

        //connect to speakers
        source.connect(audioContext.destination);
        //start playing sound
        source.start();

    } catch(playSoundError: any) {
        //set error message to console
        setConsoleMessage(playSoundError.message, true);
    }
}