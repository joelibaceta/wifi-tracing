from math import log10

def dbm2m(frequency, dBm):

  FSPL = 27.55 #Free-Space Path Loss

  m = 10 ** (( FSPL - (20 * log10(frequency)) + abs(dBm) ) / 20 )

  return round(m, 2)
   