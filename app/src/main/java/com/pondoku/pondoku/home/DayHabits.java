package com.pondoku.pondoku.home;

public class DayHabits {

    private String title;
    private String reason;

    public DayHabits(String title, String reason){
        this.title = title;
        this.reason = reason;
    }

    /**
     * Getter for DayHabits title
     * @return
     * A string of the title.
     */
    public String getDayHabitTitle(){
        return this.title;
    }

    /**
     * Getter for DayHabits reason
     * @return
     * A string of the habit reason.
     */
    public String getDayHabitReason() {
        return reason;
    }

}
