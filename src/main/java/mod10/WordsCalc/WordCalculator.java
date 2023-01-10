package WordsCalc;

import java.util.*;

public class WordCalculator{
    //private String item;
    private List<WordCalcArray> numbers;

    private ArrayList<String> strArr;

    public WordCalculator(ArrayList<Character> chars) {
        this.strArr = splitter(chars);
        this.numbers = calculcator(strArr);
        sorter(numbers);
        //System.out.println(numbers);

    }

    static class WordCalcArray{
        String name;
        int number;

        public WordCalcArray(String s, int index) {
            this.name = s;
            this.number = index;
        }

        public String getName() {
            return name;
        }

        public int getNumber() {
            return number;
        }
    }
    //Sort by amout the list numbers
    private void sorter(List<WordCalcArray> numbers) {
        ArrayList<WordCalcArray> sort = new ArrayList<>();
        Collections.sort(numbers, new Comparator<WordCalcArray>() {
            @Override
            public int compare(WordCalcArray o1, WordCalcArray o2) {
                return o2.getNumber()-o1.getNumber();
            }
        });
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i).getName() + " " + numbers.get(i).getNumber());

        }
    }

    //Compare numbers and calculate amount of the frequency of the word. And create the list of objects of WordCalcArray
    private List<WordCalcArray> calculcator(ArrayList<String> strArr){
        ArrayList<WordCalcArray> result = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        int index = 0;
        for (String s : strArr) {
            for (int i = 0; i < strArr.size(); i++) {
                if (s.equals(strArr.get(i))){
                    index++;
                }
            }
            if (!(list.contains(s))) {
                result.add(new WordCalcArray(s, index));
                list.add(s);

            }
            index = 0;
        }
        return result;
    }



    //Splitting the chars into the words
    private ArrayList<String> splitter(ArrayList<Character> chars) {
        ArrayList<String> strArr = new ArrayList<>();
        String string = "";
        for (Character aChar : chars) {
            if (Objects.equals(aChar, null) || Objects.equals(aChar, ' ') || Objects.equals(aChar, '\n')) {
                if (string.length() >= 1) {
                    strArr.add(string);
                    string = "";
                }
            }
            if (!(Objects.equals(aChar, null) || Objects.equals(aChar, ' ') || Objects.equals(aChar, '\n'))) {
                string += aChar;
            }
        }
        if (string != "") {
            strArr.add(string);
            string = "";
        }
        return strArr;
    }


}
