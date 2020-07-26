from time import sleep
from lib import iwlist
from lib import calc

import requests

content = iwlist.scan(interface='wlan0')
cells = iwlist.parse(content)

# For demo purpose only

REFERENCE_NETWORKS = [
  "dlink-FA14",
  "ADMIN*",
  "Internet Joel"
]

def get_distances():
  counter = 0
  distances = []
  
  uri = "http://198.199.73.28:3000/positions/save"

  for cell in cells:
    if cell["essid"] in REFERENCE_NETWORKS:
      mhz = float(cell["frequency"]) * 1000
      dbm = float(cell["signal_level_dBm"]) 
      distance = calc.dbm2m(mhz, dbm)

      distances.append(distance)

      counter += 1

      # print(cell["essid"])
      # print(cell["signal_quality"])
      # print(cell["signal_total"])
      # print(cell["signal_level_dBm"])
      # print(distance)

  payload = {"distances": distances}

  r = requests.post(uri, data=payload)

  print(r.text)

while(True):
  sleep(5)
  get_distances()