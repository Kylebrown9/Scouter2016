package org.ncfrcteams.frcscoutinghub2016.matchdata.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kyle Brown on 3/16/2016.
 */
public class Schedule {
    private List<MatchDescriptor> matchDescriptorList;
    private List<Match> matches;
    private ScheduleChangeListener scheduleChangeListener = null;

    //****************************************UI Methods*********************************

    /**
     * Creates and initializes a new Schedule object
     */
    public Schedule() {
        matchDescriptorList = new ArrayList<>();
        matches = new ArrayList<>();
    }

    /**
     * Sets the ScheduleChangeListener for this Schedule
     * @param changeListener
     */
    public synchronized void setScheduleChangeListener(ScheduleChangeListener changeListener) {
        this.scheduleChangeListener = changeListener;
    }

    /**
     * Adds a single MatchDescriptor to the matchDescriptorList sorts it then updates matches
     * and informs the scheduleChangeListener of the changes
     * @param matchDescriptor the MatchDescriptor to be added
     */
    public synchronized void add(MatchDescriptor matchDescriptor) {
        this.matchDescriptorList.add(matchDescriptor);
        update();
    }

    /**
     * Adds a list of MatchDescriptors to the matchDescriptorList sorts it then updates the
     * scheduleEntriesList and informs the scheduleChangeListener of the changes
     * @param matchDescriptorList the list of MatchDescriptors to be added
     */
    public synchronized void addAll(List<MatchDescriptor> matchDescriptorList) {
        this.matchDescriptorList.addAll(matchDescriptorList);
        update();
    }

    /**
     * @return an up to date copy of the scheduleEntries list
     */
    public synchronized List<Match> getMatches() {
        return new ArrayList<>(matches);
    }

    private void update() {
        Collections.sort(matchDescriptorList);
        matches.clear();

        int lastNum = 1;
        int currNum;
        Match newMatch;
        boolean lastMatchWasQual = matchDescriptorList.get(0).isQual();

        for(MatchDescriptor matchDescriptor : matchDescriptorList) {
            currNum = matchDescriptor.getMatchNum();

            if(!matchDescriptor.isQual() && lastMatchWasQual) {
                lastNum = 1;
            }

            for(int i=lastNum; i<currNum; i++) {
                newMatch = Match.getBlank(i,matchDescriptor.isQual());
                matches.add(newMatch);
            }

            newMatch = Match.getFromDescriptor(matchDescriptor);
            matches.add(newMatch);

            lastNum = currNum;
            lastMatchWasQual = matchDescriptor.isQual();
        }

        if(scheduleChangeListener != null) {
            scheduleChangeListener.notifyScheduleChanges(getMatches());
        }
    }

    public interface ScheduleChangeListener {
        void notifyScheduleChanges(List<Match> matches);
    }
}
