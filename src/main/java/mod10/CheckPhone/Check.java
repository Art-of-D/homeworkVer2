package CheckPhone;

import java.util.ArrayList;
import java.util.Objects;

public class Check {

    ArrayList<String> phoneList = new ArrayList<>();
    ArrayList<String> crctPhoneList = new ArrayList<>();
    char[] phoneNumber;
    Character[] charArray;

    public Check(char[] phoneNumber) {
        this.phoneNumber = phoneNumber;
        charArray = new Character[phoneNumber.length];
        for (int i = 0; i < phoneNumber.length - 1; i++) charArray[i] = phoneNumber[i]; //перетворєєм char y Character
        crctFull();
    }

    public ArrayList<String> getCrctPhoneList() {
        return crctPhoneList;
    }


    private ArrayList<String> crctFull() {
        //Цей метод робить повну перевірку номерів
        this.phoneList = createNumbers();
        if (phoneList.size() >= 1) {
            secondCheck();
            return crctPhoneList;
        }
        return new ArrayList<String>(null);
    }

    private ArrayList<String> createNumbers() {
        //Проходить по масиву на розділяє на поперенді номери
        ArrayList<String> transientArray = new ArrayList<>();
        int j = 0;
        while (j < phoneNumber.length) {
            if (charArray[j].equals('(')) {
                String curveNmbr = "";
                for (int index1 = 0; index1 < 14; index1++) {
                    curveNmbr += Character.toString(charArray[j]);
                    j++;
                }
                transientArray.add(curveNmbr);
            } else if (Character.isDigit(charArray[j])) {
                String digitNmbr = "";
                for (int index2 = 0; index2 < 12; index2++) {
                    digitNmbr += Character.toString(charArray[j]);
                    j++;
                }
                transientArray.add(digitNmbr);
            }
            j++;
        }
        return transientArray.get(0) == null ? new ArrayList<String>(null) : transientArray;
    }

    private void secondCheck() {
        for (String word : phoneList) {
            if (!(Objects.equals(word, null))) {
                char[] transArray = word.toCharArray();
                if (transArray[0] == '(') {
                    if (fType(transArray)) {
                        if (word.length() == 14) {
                            crctPhoneList.add(word);
                        }
                    }
                    } else if (sType(transArray)) {
                    if(word.length() == 12) {
                        crctPhoneList.add(word);
                    }
                }
            }
        }
    }


    private boolean fType(char[] transArray){
            boolean a = false, b = false, c = false, d = false;
            int index4 = 1;
            int[] intArray = {-1, 1, 2, 3, -1, -1, 6, 7, 8, -1, 10, 11, 12, 13};
            while (index4 < transArray.length) {
                if (index4 == intArray[index4]) {
                    if (Character.isDigit(transArray[index4])) {
                        b = true;
                        index4++;
                    } else {
                        b = false;
                        break;}
                    if (index4 == 4) {
                        if (transArray[index4] == ')') a = true;
                        index4++;
                    }
                    if (index4 == 5) {
                        if (transArray[index4] == ' ') c = true;
                        index4++;
                    }
                    if (index4 == 9) {
                        if (transArray[index4] == '-') d = true;
                        index4++;
                    }
                }
            }
            return (a && b && c && d);
    }

        private boolean sType ( char[] transArray){
            boolean a = false, b = false;
            int index4 = 0;
            int[] intArray = {0, 1, 2, -1, 4, 5, 6, -1, 8, 9, 10, 11};
            while (index4 < transArray.length) {
                if (index4 == intArray[index4]) {
                    if (Character.isDigit(transArray[index4])) {
                        a = true;
                        index4++;
                    } else {
                        a = false;
                        break;
                    }
                }
                if (index4 == 3 || index4 == 7) {
                    if (transArray[index4] == '-') b = true;
                    index4++;
                }
            }
            return (a && b);
        }
}
