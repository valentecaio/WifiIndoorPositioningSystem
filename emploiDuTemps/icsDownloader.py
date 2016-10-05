from wget import download
from os import system, rename, listdir
import sys

def ics_download(url, filename):
	file = download(url)
	system('mv -vf ' + file + ' ./ics/' + filename + '.ics')

if __name__ == '__main__':
	if len(sys.argv) is not 2:
		print ("Usage: python ", sys.argv[0], " <path of ics file>")
		#exit()
	
	system('rm -rf ADECal.ics')
	url_part1 = "http://edt.telecom-bretagne.eu/jsp/custom/modules/plannings/anonymous_cal.jsp?resources="
	url_part2 = "&projectId=1&calType=ical&firstDate=2016-09-26&lastDate=2016-10-02"
	for i in range (0, 8000):
		filename = str(i)
		url = url_part1 + filename + url_part2
		ics_download(url, filename)

'''
rename(file, 'CaioValente.ics')
dir = listdir('.')
print (dir)
'''

