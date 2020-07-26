from lib import iwlist

content = iwlist.scan(interface='wlan0')
cells = iwlist.parse(content)

for cell in cells:
  print(cell["essid"])
  print(cell["signal_quality"])
  print(cell["signal_total"])
  print(cell["signal_level_dBm"])