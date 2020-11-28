import random

asuID = 1210000000
fNames = []
lNames = []
PROGRAMS = ["Computer Science", "Computer Systems Engineering"]
ACADEMIC_LEVEL = ["Undergrad", "Graduate"]
asurite = []
team = ["agaouette", "mmassoudi", "dragipi", "orios", "cwoehler"]

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
    for i in range(0, numStudents):
        addID = asuID + random.randint(0, 10000000)
        randomFirst = random.choice(fNames)
        randomLast =  random.choice(lNames)
        asuriteSTR = randomFirst[0].lower() + randomLast.lower()
        program = random.choice(PROGRAMS)
        level = random.choice(ACADEMIC_LEVEL)
        if asuriteSTR not in asurite:
            asurite.append(asuriteSTR)
        else: 
            while asuriteSTR in asurite:
                asuriteSTR = asuriteSTR + str(random.randint(0, 100))
            asurite.append(asuriteSTR)
        roster.write(str(addID) + "," + randomFirst + "," + randomLast + "," + program + "," + level + "," + asurite[i] + "\n")
    roster.close()

    numAttendanceFiles = int(input("How many attendance CSV files should I generate? (1-10): "))
    visitors = (input("Add visitors to classroom? (yes/no): "))
    if visitors == "yes":
        response = True
    elif visitors == "no":
        response = False
    else:
        print("Response must be yes or no. Not adding visitors.")
        response = False

    while (numAttendanceFiles < 11 and numAttendanceFiles > 0):
        filename = "attendance" + str(numAttendanceFiles) + ".csv"
        attendance = open(filename, "w")
        for student in asurite:
            # Check if asurite contains a number if it does we'll make them connect
            # multiple times, they'll connect between 1-5 times for a maximum of
            # 75 minutes
            if any(map(str.isdigit, student)):
                bound = random.randint(1, 5)
                for i in range (1, bound):
                    time = random.randint(1, 15)
                    attendance.write(student + "," + str(time) + "\n")
            else:
                time = random.randint(1, 75)
                attendance.write(student + "," + str(time) + "\n")

        if response == True:
            # One of us is in the wrong class
            visitor = random.choice(team)
            time = random.randint(1, 75)
            attendance.write(visitor + "," + str(time) + "\n")
        attendance.close()
        numAttendanceFiles -= 1