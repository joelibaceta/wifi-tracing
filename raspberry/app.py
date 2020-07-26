from lib import iwlist
from lib import calc

content = iwlist.scan(interface='wlan0')
cells = iwlist.parse(content)

# For demo purpose only

REFERENCE_NETWORKS = [
  "dlink-FA14",
  "ADMIN*",
  "Internet Joel"
]

for cell in cells:
  if cell["essid"] in REFERENCE_NETWORKS:
    mhz = float(cell["frequency"]) * 1000
    distance = dbm2m(mhz, signal_level_dBm)
    print(cell["essid"])
    print(cell["signal_quality"])
    print(cell["signal_total"])
    print(cell["signal_level_dBm"])
    print(distance)