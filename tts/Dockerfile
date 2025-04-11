FROM python:3.9-slim

RUN apt-get -y update
RUN apt-get -y install git

WORKDIR /app

COPY . /app

RUN pip install git+https://github.com/suno-ai/bark.git

#RUN pip install -r app/requirements.txt

EXPOSE 80

ENTRYPOINT ["python", "-u", "app/app.py"]
