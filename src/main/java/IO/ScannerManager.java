package IO;

import data.*;
import exceptions.*;
import utilities.CollectionManager;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static config.ConfigData.inputInfo;
import static data.Coordinates.MAX_X;
import static data.Coordinates.MIN_Y;
import static data.StudyGroup.*;


public class ScannerManager {
    private static boolean filemode;
    public static final Pattern patternSymbols = Pattern.compile("\\w*");
    public static final Pattern patternNumber = Pattern.compile("(-?)\\d+(.\\d+)?");

    public static final Pattern patterndigits = Pattern.compile("\\d*");

    public static Scanner scannerScript;


    public static int askPort() {
        boolean success = false;
        int port = 0;
        System.out.println("Enter port to connect");
        while ((!success)) {
            try {
                Scanner scanner = new Scanner(System.in);
                port = Integer.parseInt(scanner.nextLine());
                if (!(port >= 1000 && port < 30000)) {
                    throw new RuntimeException("incorrect  port, try again");
                }
                success = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(port);
        return port;
    }

    public static String askCommand() {
        String command = "";
        while (command.equals("")) {
            System.out.print("Enter command: ");
            Scanner in = new Scanner((System.in));
            command = in.nextLine();
        }
        return command;
    }

    public static StudyGroup askGroup(CollectionManager collectionManager, boolean runScript, Scanner scriptScanner) throws IncorrectScriptException, IncorrectValuesForGroupException {
        return new StudyGroup(
                wrongId,
                askGroupName(runScript, scriptScanner),
                askCoordinates(runScript, scriptScanner),
                collectionManager.getLastInitTime().now(),
                askStudentCount(runScript, scriptScanner),
                askShouldBeExpelled(runScript, scriptScanner),
                askAverageMark(runScript, scriptScanner),
                askSemesterEnum(runScript, scriptScanner),
                askPerson(runScript, scriptScanner));

    }

    public static String askName(String inputTitle, String typeOfName, boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        String name;
        Scanner scanner;
        while (true) {
            try {
                System.out.println(inputTitle);
                System.out.print(inputInfo);
                if (runScript) {
                    scanner = scriptScanner;
                } else {
                    scanner = new Scanner(System.in);
                }
                name = scanner.nextLine().trim();
                if (runScript) System.out.println(name);
                if (name.equals("")) throw new NotNullException();
                if (!patternSymbols.matcher(name).matches()) throw new WrongNameException();
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError(String.format("%s can't be empty!!!", typeOfName));
                if (runScript) throw new IncorrectScriptException();
            } catch (WrongNameException e) {
                ConsoleManager.printError("I can parse only char symbol! (letters, numbers and '_')");
                if (runScript) throw new IncorrectScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Name is ctrl+D. ok, bye");
                if (runScript) throw new IncorrectScriptException();
                System.exit(0);
            }
        }
        return name;
    }

    public static String askGroupName(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        return askName("Enter Study Group name", "Study Group name", runScript, scriptScanner);
    }

    public static String askPersonName(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        return askName("Enter Admin name:", "Person name", runScript, scriptScanner);
    }


    public static Double askCoordinatesX(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        String userX;
        Double x;
        Scanner scanner;
        while (true) {
            try {
                System.out.println("Enter X coordinate: ");
                System.out.print(inputInfo);
                if (runScript) {
                    scanner = scriptScanner;
                } else {
                    scanner = new Scanner(System.in);
                }
                userX = scanner.nextLine().trim();
                if (userX.equals("")) throw new NotNullException();
                if (!patternNumber.matcher(userX).matches()) throw new WrongNameException();
                if (userX.indexOf(",") > -1) {
                    userX = userX.replace(",", ".");
                }
                x = Double.parseDouble(userX);
                if (x > MAX_X) throw new IncorrectValueException();
                break;
            } catch (NumberFormatException e) {
                ConsoleManager.printError("Given String is not parsable to Double");
                if (runScript) throw new IncorrectScriptException();
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!!");
                if (runScript) throw new IncorrectScriptException();
            } catch (WrongNameException e) {
                ConsoleManager.printError("hmm.. You use symbols not for numbers... why?");
                if (runScript) throw new IncorrectScriptException();
            } catch (IncorrectValueException e) {
                ConsoleManager.printError("This value has to be less than " + MAX_X);
                if (runScript) throw new IncorrectScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("X is ctrl+D. ok, bye");
                if (runScript) throw new IncorrectScriptException();
                System.exit(0);
            }
        }
        return x;

    }

    public static String askArgForCmd() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static Float askCoordinatesY(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        String userY;
        Float y;
        Scanner scanner;
        while (true) {
            try {
                System.out.println("Enter Y coord:");
                System.out.print(inputInfo);
                if (runScript) {
                    scanner = scriptScanner;
                } else {
                    scanner = new Scanner(System.in);
                }
                userY = scanner.nextLine().trim();
                if (userY.equals("")) throw new NotNullException();
                if (!patternNumber.matcher(userY).matches()) throw new WrongNameException();
                if (userY.indexOf(",") > -1) {
                    userY = userY.replace(",", ".");
                }
                y = Float.parseFloat(userY);
                if (y < MIN_Y) throw new IncorrectValueException();
                break;
            } catch (NumberFormatException e) {
                ConsoleManager.printError("Given String is not parsable to Float");
                if (runScript) throw new IncorrectScriptException();
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!!");
                if (runScript) throw new IncorrectScriptException();
            } catch (WrongNameException e) {
                ConsoleManager.printError("hmm.. You use symbols not for numbers... why?");
                if (runScript) throw new IncorrectScriptException();
            } catch (IncorrectValueException e) {
                ConsoleManager.printError("This value has to be more than " + MIN_Y);
                if (runScript) throw new IncorrectScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Y is ctrl+D. ok, bye");
                if (runScript) throw new IncorrectScriptException();
                System.exit(0);
            }

        }
        return y;
    }

    public static Coordinates askCoordinates(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        try {
            Double x = askCoordinatesX(runScript, scriptScanner);
            Float y = askCoordinatesY(runScript, scriptScanner);
            Coordinates userCoordinates = new Coordinates();
            userCoordinates.setX(x);
            userCoordinates.setY(y);
            return userCoordinates;
        } catch (IncorrectValuesForGroupException e) {
            ConsoleManager.printError(e);
            return null;
        }
    }

    public static int askStudentCount(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException, NumberFormatException {
        String userCount;
        int count;
        Scanner scanner;
        while (true) {
            try {
                System.out.println("Enter the number of students in a group:");
                System.out.print(inputInfo);
                if (runScript) {
                    scanner = scriptScanner;
                } else {
                    scanner = new Scanner(System.in);
                }
                userCount = scanner.nextLine().trim();
                if (userCount.equals("")) throw new NotNullException();
                count = Integer.parseInt(userCount);
                if (count <= 0) throw new IncorrectValueException();
                break;
            } catch (NotNullException | IncorrectValueException e) {
                ConsoleManager.printError("Are you sure it could be the number of students??");
                if (runScript) throw new IncorrectScriptException();
            } catch (NumberFormatException e) {
                ConsoleManager.printError("Given String is not parsable to int");
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("The number of students is ctrl+D. ok, bye");
                if (runScript) throw new IncorrectScriptException();
                System.exit(0);
            }
        }
        return count;
    }

    public static Integer askShouldBeExpelled(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException, NumberFormatException {
        String userCountExpelled;
        Integer countExpelled;
        Scanner scanner;
        while (true) {
            try {
                System.out.println("Enter the number of students to be expelled:");
                System.out.print(inputInfo);
                if (runScript) {
                    scanner = scriptScanner;
                } else {
                    scanner = new Scanner(System.in);
                }
                userCountExpelled = scanner.nextLine().trim();
                if (userCountExpelled.equals("")) throw new NotNullException();
                countExpelled = Integer.parseInt(userCountExpelled);
                if (countExpelled <= 0) throw new IncorrectValueException();
                break;
            } catch (NotNullException e) {
                return null;
            } catch (IncorrectValueException e) {
                ConsoleManager.printError("It has to be more than 0");
                if (runScript) throw new IncorrectScriptException();
            } catch (NumberFormatException e) {
                ConsoleManager.printError("Given String is not parsable to Integer");
                if (runScript) throw new IncorrectScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("The number of students to be expelled is ctrl+D. ok, bye");
                if (runScript) throw new IncorrectScriptException();
                System.exit(0);
            }
        }
        return countExpelled;
    }

    public static double askAverageMark(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        String userMark;
        double countMark;
        Scanner scanner;
        while (true) {
            try {
                System.out.println("Enter average mark:");
                System.out.print(inputInfo);
                if (runScript) {
                    scanner = scriptScanner;
                } else {
                    scanner = new Scanner(System.in);
                }
                userMark = scanner.nextLine().trim();
                countMark = Double.parseDouble(userMark);
                if (countMark <= 0) throw new IncorrectValueException();
                break;
            } catch (IncorrectValueException e) {
                ConsoleManager.printError("It has to be more than 0");
                if (runScript) throw new IncorrectScriptException();
            } catch (NumberFormatException e) {
                ConsoleManager.printError("Given String is not parsable to double");
                if (runScript) throw new IncorrectScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Average mark is ctrl+D. ok, bye");
                if (runScript) throw new IncorrectScriptException();
                System.exit(0);
            }
        }
        return countMark;
    }

    public static Semester askSemesterEnum(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        String userSemester;
        Semester semester;
        Integer semesterId;
        Scanner scanner;
        while (true) {
            try {
                System.out.println("Semester list - " + Semester.getList());
                System.out.println("Enter your semester:");
                System.out.print(inputInfo);
                if (runScript) {
                    scanner = scriptScanner;
                } else {
                    scanner = new Scanner(System.in);
                }
                userSemester = scanner.nextLine().trim();
                if (userSemester.equals("")) throw new NotNullException();

                try {
                    semesterId = Integer.parseInt(userSemester);
                    if (semesterId >= Semester.values().length - 1 || semesterId < 0) {
                        throw new IncorrectIndexInOrdinalEnumException();
                    }
                    semester = Semester.values()[semesterId];
                    ConsoleManager.printInfoPurpleBackground(semester);
                    break;
                } catch (NumberFormatException e) {
                    semester = Semester.valueOf(userSemester.toUpperCase());
                    break;
                } catch (IncorrectIndexInOrdinalEnumException e) {
                    ConsoleManager.printError("I don't know this semester(");
                    if (runScript) throw new IncorrectScriptException();
                }

            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!");
                if (runScript) throw new IncorrectScriptException();
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this semester(");
                if (runScript) throw new IncorrectScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Semester is ctrl+D. ok, bye");
                if (runScript) throw new IncorrectScriptException();
                System.exit(0);
            }
        }
        return semester;
    }

    public static Person askPerson(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        if (askQuestion("Is there an admin?", runScript, scriptScanner)) {
            return new Person(askPersonName(runScript, scriptScanner), askBirthday(runScript, scriptScanner),
                    askEyeColor(runScript, scriptScanner), askHairColor(runScript, scriptScanner), askNationality(runScript, scriptScanner));
        }
        return null;
    }

    public static Date askBirthday(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        String userDate;
        Date date;
        Scanner scanner;
        while (true) {
            try {
                System.out.println("You can use formats: 'January 19, 1970', '01/19/1970'");
                System.out.println("Enter your birthday for admin: ");
                System.out.print(inputInfo);
                if (runScript) {
                    scanner = scriptScanner;
                } else {
                    scanner = new Scanner(System.in);
                }
                userDate = scanner.nextLine().trim();
                if (userDate.equals("")) throw new NotNullException();
                date = new Date(userDate);
                break;
            } catch (NotNullException e) {
                return null;
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("You use a very strange format");
                if (runScript) throw new IncorrectScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Birthday is ctrl+D. ok, bye");
                if (runScript) throw new IncorrectScriptException();
                System.exit(0);
            }
        }

        return date;
    }

    public static ColorEye askEyeColor(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        String userEyeColor;
        ColorEye colorEye;
        Integer colorId;
        Scanner scanner;
        while (true) {
            try {
                System.out.println("Color eye list - " + ColorEye.getList());
                System.out.println("Enter your color eye:");
                System.out.print(inputInfo);
                if (runScript) {
                    scanner = scriptScanner;
                } else {
                    scanner = new Scanner(System.in);
                }
                userEyeColor = scanner.nextLine().trim();
                if (userEyeColor.equals("")) throw new NotNullException();
                try {
                    colorId = Integer.parseInt(userEyeColor);
                    if (colorId >= Semester.values().length - 1 || colorId < 0) {
                        throw new IncorrectIndexInOrdinalEnumException();
                    }

                    colorEye = ColorEye.values()[colorId];
                    ConsoleManager.printInfoPurpleBackground(colorEye);
                    break;
                } catch (NumberFormatException e) {
                    colorEye = ColorEye.valueOf(userEyeColor.toUpperCase());
                    break;
                } catch (IncorrectIndexInOrdinalEnumException e) {
                    ConsoleManager.printError("I don't know this color(");
                    if (runScript) throw new IncorrectScriptException();
                }
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!");
                if (runScript) throw new IncorrectScriptException();
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this eye color(");
                if (runScript) throw new IncorrectScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Color is ctrl+D. ok, bye");
                if (runScript) throw new IncorrectScriptException();
                System.exit(0);
            }
        }
        return colorEye;
    }

    public static ColorHair askHairColor(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        String userHairColor;
        ColorHair colorHair;
        Integer colorId;
        Scanner scanner;
        while (true) {
            try {
                System.out.println("Color hair list - " + ColorHair.getList());
                System.out.println("Enter your color hair:");
                System.out.print(inputInfo);
                if (runScript) {
                    scanner = scriptScanner;
                } else {
                    scanner = new Scanner(System.in);
                }
                userHairColor = scanner.nextLine().trim();
                if (userHairColor.equals("")) throw new NotNullException();
                try {
                    colorId = Integer.parseInt(userHairColor);
                    if (colorId >= Semester.values().length - 1 || colorId < 0) {
                        throw new IncorrectIndexInOrdinalEnumException();
                    }
                    colorHair = ColorHair.values()[colorId];
                    ConsoleManager.printInfoPurpleBackground(colorHair);
                    break;
                } catch (NumberFormatException e) {
                    colorHair = ColorHair.valueOf(userHairColor.toUpperCase());
                    break;
                } catch (IncorrectIndexInOrdinalEnumException e) {
                    ConsoleManager.printError("I don't know this color(");
                    if (filemode) throw new IncorrectScriptException();
                }
            } catch (NotNullException e) {
                return null;
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this hair color(");
                if (runScript) throw new IncorrectScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Color is ctrl+D. ok, bye");
                if (runScript) throw new IncorrectScriptException();
                System.exit(0);
            }
        }
        return colorHair;
    }

    public static Country askNationality(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        String userCountry;
        Country country;
        Integer countryId;
        Scanner scanner;
        while (true) {
            try {
                System.out.println("Country list - " + Country.getList());
                System.out.println("Enter your county:");
                System.out.print(inputInfo);
                if (runScript) {
                    scanner = scriptScanner;
                } else {
                    scanner = new Scanner(System.in);
                }
                userCountry = scanner.nextLine().trim();
                if (userCountry.isEmpty()) throw new NotNullException();
                try {
                    countryId = Integer.parseInt(userCountry);
                    if (countryId >= Semester.values().length - 1 || countryId < 0) {
                        throw new IncorrectIndexInOrdinalEnumException();
                    }
                    country = Country.values()[countryId];
                    ConsoleManager.printInfoPurpleBackground(country);
                    break;
                } catch (NumberFormatException e) {
                    country = Country.valueOf(userCountry.toUpperCase());
                    break;
                } catch (IncorrectIndexInOrdinalEnumException e) {
                    ConsoleManager.printError("I don't know this country(");
                    if (runScript) throw new IncorrectScriptException();
                }
            } catch (NotNullException e) {
                return null;
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this nationality(");
                if (runScript) throw new IncorrectScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Country is ctrl+D. ok, bye");
                if (runScript) throw new IncorrectScriptException();
                System.exit(0);
            }
        }
        return country;
    }

    public static StudyGroup askQuestionForUpdate(boolean runScript, Scanner scriptScanner) throws IncorrectScriptException, IncorrectValuesForGroupException {
        String name = wrongName;
        Coordinates coordinates = wrongCoordinates;
        LocalDateTime creationDate = LocalDateTime.now();
        int studentsCount = WRONG_STUDENT_COUNT;
        Integer shouldBeExpelled = wrongShouldBeExpelled;
        double averageMark = WRONG_AVERAGE_MARK;
        Semester semesterEnum = wrongSemesterEnum;
        Person groupAdmin = new Person();
        if (askQuestion("Change study group name?", runScript, scriptScanner)) {
            name = askGroupName( runScript, scriptScanner);
        }
        if (askQuestion("Change study group coordinates?", runScript, scriptScanner)) {
            coordinates = askCoordinates( runScript, scriptScanner);
        }
        if (askQuestion("Change the number of students in a group??", runScript, scriptScanner)) {
            studentsCount = askStudentCount(runScript, scriptScanner);
        }
        if (askQuestion("Change the number of students to be expelled??", runScript, scriptScanner)) {
            shouldBeExpelled = askShouldBeExpelled(runScript, scriptScanner);
        }
        if (askQuestion("Change study group average mark?", runScript, scriptScanner)) {
            averageMark = askAverageMark(runScript, scriptScanner);
        }
        if (askQuestion("Change study group semester?", runScript, scriptScanner)) {
            semesterEnum = askSemesterEnum(runScript, scriptScanner);
        }
        if (askQuestion("Change study group admin?", runScript, scriptScanner)) {
            groupAdmin = askPerson(runScript, scriptScanner);
        }
        return new StudyGroup(wrongId, name, coordinates, creationDate, studentsCount,
                shouldBeExpelled, averageMark, semesterEnum, groupAdmin);

    }

    public static boolean askQuestion(String question, boolean runScript, Scanner scriptScanner) throws IncorrectScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        Scanner scanner;
        while (true) {
            try {
                System.out.println(finalQuestion);
                System.out.println(inputInfo);
                if (runScript) {
                    scanner = scriptScanner;
                } else {
                    scanner = new Scanner(System.in);
                }
                answer = scanner.nextLine().trim();
                if (answer.equals("")) throw new NotNullException();
                if (!(answer.equals("+") || answer.equals("-"))) throw new IncorrectValueException();
                break;
            } catch (NotNullException e) {
                System.out.println("I know that silence is golden. But what should I do with it? I only understand + and -");
                if (runScript) throw new IncorrectScriptException();
            } catch (IncorrectValueException e) {
                System.out.println("I believed that you are a smart person and able to distinguish other characters from +/-");
                if (runScript) throw new IncorrectScriptException();
            } catch (NoSuchElementException e) {
                System.out.println("Answer is ctrl+D. ok, bye");
                if (runScript) throw new IncorrectScriptException();
                System.exit(0);
            }
        }
        return answer.equals("+");
    }






    public static void setScanner(Scanner scriptScanner) {
        scannerScript = scriptScanner;
    }

}
