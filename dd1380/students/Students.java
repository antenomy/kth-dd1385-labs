package students;

import java.util.ArrayList;
import java.util.Collections;

public class Students {
    public static int iterations = 0;

    public static boolean is_numeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static boolean is_earlier_alphabetically(String a, String b) {
        return a.compareTo(b) > 0;
    }

    // EFFICIENT
    public static int find_nearest_date(int date) {
        int day_numbers = date % 100;
        int month_numbers = date % 10000 - day_numbers;
        int year_numbers = date - month_numbers - day_numbers;

        if (day_numbers <= 14 && day_numbers > 0) {
            day_numbers = 15;
        } else if (day_numbers >= 15) {
            day_numbers = 1;
            if (month_numbers >= 1200) {
                month_numbers = 100;
                year_numbers += 10000;
            } else {
                month_numbers += 100;                
            }
        }

        return year_numbers + month_numbers + day_numbers;
    }

    // EFFICIENT
    public static int next_date(int date, ArrayList<PresentationDay> presentation_days) {
        if (!presentation_days.isEmpty() && date < presentation_days.getLast().date) {
            return find_nearest_date(presentation_days.getLast().date);
        } else {
            return find_nearest_date(date);
        }
    }


    public static void main(String[] args) {
        //Input Part
        Kattio io = new Kattio(System.in, System.out);
        ArrayList<String> new_student_info = new ArrayList<>();
        int list_size;
        int date;

        ArrayList<StudentData> students = new ArrayList<>();
        String next_word;

        while (io.hasMoreTokens()) {
            while (io.hasMoreTokens()) {
                next_word = io.getWord();
                new_student_info.add(next_word);

                if (is_numeric(next_word)) break;
            }
            
            list_size = new_student_info.size()-1;
            date = Integer.parseInt(new_student_info.remove(list_size));
            students.add(new StudentData(new_student_info.toArray(new String[list_size]), date));
            new_student_info = new ArrayList<>();
        }

        // Sort
        Collections.sort(students);
        
        // Day Allocation
        ArrayList<PresentationDay> presentation_days = new ArrayList<>();
        presentation_days.add(new PresentationDay(next_date(students.get(0).presentation_date, presentation_days), new ArrayList<>()));
        
        PresentationDay current_day = presentation_days.getLast();

        for (StudentData student : students) {
            // System.out.println(student.first_name);
            if (current_day.students.size() >= 5) {
                // System.out.println(current_day.students.size());
                StudentData last_student = current_day.students.getLast();

                // System.out.println(last_student.presentation_date + " " + student.presentation_date);
                // System.out.println(last_student.last_name + " " + student.last_name);

                // last_student.presentation_date == student.presentation_date && 
                if (!last_student.last_name.equals(student.last_name)) {
                    presentation_days.add(new PresentationDay(next_date(student.presentation_date, presentation_days), new ArrayList<>()));
                    current_day = presentation_days.getLast();
                }
            } else if(student.presentation_date >= current_day.date) {
                presentation_days.add(new PresentationDay(next_date(student.presentation_date, presentation_days), new ArrayList<>()));
                current_day = presentation_days.getLast();
            }

            current_day.students.add(student);
        }

        // Print Area
        for (PresentationDay day : presentation_days) {
            System.out.println(day.date);
            for (StudentData student : day.students) {
                System.out.println(String.join(" ", student.names));
            }
        }
    }
}