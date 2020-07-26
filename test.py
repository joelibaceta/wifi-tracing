from lib import iwlist

content = iwlist.scan(interface='wlan0')
cells = iwlist.parse(content)

print(cells)