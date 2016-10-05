# modulo que le uma tabela exportada em .ics
# e gera um dict com as informacoes lidas

import sys

_EVENTBEGIN = 'BEGIN:VEVENT\n'
_EVENTEND = 'END:VEVENT\n'
_SUMMARY = 'SUMMARY'
_DTSTART = 'DTSTART'
_DTEND = 'DTEND'
_LOCATION = 'LOCATION'
_DESCRIPTION = 'DESCRIPTION'

# acha os indices de uma informacao em um evento
# in: evento como string; nome da informacao desejada (capital letters)
# out: indices de start e end dos dados da informacao
def findInfoInEvent(event, infoName):
	infoStr = infoName + ':'
	start = event.find(infoStr) + len(infoStr)
	end = event.find('\n', start)
	return start, end

# le um arquivo ics e decripta a informacao
# in: nome do arquivo
# out: array de dict com os eventos encontrados no arquivo
def ics_getDataFromICS(filename):
	# le o arquivo
	icsFile = open(filename, "r")
	icsStr = icsFile.read()
	
	# separa os eventos
	events = []
	while True: # ta feio mas um dia eu melhoro
		start = icsStr.find(_EVENTBEGIN)
		# para quando nao acha um evento
		if start == -1:
			break
		# corrige o start pra pular a flag VEVENT
		start +=  len(_EVENTBEGIN)

		end = icsStr.find(_EVENTEND, start)
		if end == -1:
			end = len(icsStr) - 1

		events.append(icsStr[start:end])

		# 'recorta' o evento lido do arquivo para ler o proximo
		icsStr = icsStr[end + len(_EVENTEND):]
	
	# separa os dados de cada evento
	eventsDict = [{}]
	for e in events:
		eventInfos = {}
		for entry in [_SUMMARY, _DTSTART, _DTEND, _LOCATION, _DESCRIPTION]:
			start, end = findInfoInEvent(e, entry)
			eventInfos[entry] = e[start:end]
		eventsDict.append(eventInfos)
	return eventsDict

if __name__ == '__main__':
	if len(sys.argv) is not 2:
		print ("Usage: python ", sys.argv[0], " <path of ics file>")
		exit()
	data = ics_getDataFromICS(sys.argv[1])
	for d in data:
		print (d)


