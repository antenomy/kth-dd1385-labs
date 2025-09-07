package students;

import java.lang.Comparable;

public class StudentData implements Comparable<StudentData> {

    String[] names;
    String first_name;
    String last_name;
    int presentation_date;


    
    @Override
    public int compareTo(StudentData other) {
        // System.out.println("Comparing " + String.join(" ", this.names) + " to " + String.join(" ", other.names));

        if (this.presentation_date == other.presentation_date) {
            if (this.names.equals(other.names)) {
                return 0;
            } else if (this.last_name.equals(other.last_name)) {
                // Here we check each first name to find the higher alphabetical name

                // Only iterate over the lowest amount of names, minus last name
                int lowest_name_count = Math.min(this.names.length, other.names.length) - 1;

                // Check alphabetical heirarchy for each name
                for (int i = 0; i < lowest_name_count; i++) {
                    int comparison_value = this.names[i].compareTo(other.names[i]);

                    if (comparison_value != 0) {
                        return comparison_value;
                    }
                }

                // All same names but one has more -> the one with more goes last
                if (this.names.length > other.names.length) {
                    return 1;
                } else if (this.names.length == other.names.length) {
                    return 0;
                } else {
                    return -1;
                }

                //need to check if the begining of the string is the same as the other, then the longer wins
                //need to check alphabetical order of each name

            } else {
                return this.last_name.compareTo(other.last_name);
            }
        } else if (this.presentation_date > other.presentation_date) {
            return 1;
        } else {
            return -1;
        }
    }

    public StudentData(String[] names, int presentation_date) {
        this.names = names;
        this.presentation_date = presentation_date;
        this.first_name = names[0];
        this.last_name = names[names.length - 1];
    }
}
