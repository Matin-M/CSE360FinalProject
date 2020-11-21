import random

asuID = 1210000000
fNames = []
lNames = []
programs = ["Computer Science BS", "Computer Systems Engineering BSE"]
ACADEMIC_LEVEL = "Undergrad"
asurite = []

numStudents = int(input("How many students should I generate? (max 300): "))

if numStudents > 0 and numStudents < 301:   
    fnameFile = open("firstnames.txt", "r")
    for line in fnameFile:
        fNames.append(line.strip())
    fnameFile.close()
    
    lnameFile = open("lastnames.txt", "r")
    for line in lnameFile:
        lNames.append(line.strip())
    lnameFile.close()

    roster = open("roster.csv", "w")
    for i in range(0, numStudents + 1):
        addID = asuID + random.randint(0, 10000000)
        randomFirst = random.choice(fNames)
        randomLast =  random.choice(lNames)
        asuriteSTR = randomFirst[0].lower() + randomLast.lower()
        program = random.choice(programs)
        if asuriteSTR not in asurite:
            asurite.append(asuriteSTR)
        else: 
            while asuriteSTR in asurite:
                asuriteSTR = asuriteSTR + str(random.randint(0, 100))
            asurite.append(asuriteSTR)
        roster.write(str(addID) + "," + randomFirst + "," + randomLast + "," + program + "," + ACADEMIC_LEVEL+ "," + asurite[i] + "\n")
    roster.close()