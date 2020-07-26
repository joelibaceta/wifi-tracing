FROM python:3

RUN apt update
RUN apt install iw -y

WORKDIR /usr/src/app

COPY requirements.txt ./
RUN pip3 install --no-cache-dir -r requirements.txt

COPY . .

CMD [ "python", "./test.py" ]