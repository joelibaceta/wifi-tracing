from time import sleep
from lib import iwlist
from lib import calc

import requests



# For demo purpose only

REFERENCE_NETWORKS = [
  "dlink-FA14",
  "ADMIN*",
  "Internet Joel"
]

def get_distances():
  content = iwlist.scan(interface='wlan0')
  cells = iwlist.parse(content)

  counter = 0
  distances = {}
  
  uri = "http://198.199.73.28:3000/positions/save"

  for cell in cells:
    if cell["essid"] in REFERENCE_NETWORKS:
      mhz = float(cell["frequency"]) * 1000
      dbm = float(cell["signal_level_dBm"]) 
      essid = cell["essid"]

      distance = calc.dbm2m(mhz, dbm)

      distances[essid] = distance

      counter += 1


      print(essid + ":" +  str(distance))
      # print(cell["signal_quality"])
      # print(cell["signal_total"])
      # print(cell["signal_level_dBm"]) 

  payload = {"distances": [
    distances["dlink-FA14"],
    distances["ADMIN*"] / 2,
    distances["Internet Joel"] 
  ], "id": "raspberry"}

  r = requests.post(uri, data=payload)

  print(r.text)

while(True):
  sleep(5)
  get_distances()