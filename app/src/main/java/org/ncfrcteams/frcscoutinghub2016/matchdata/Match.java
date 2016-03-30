package org.ncfrcteams.frcscoutinghub2016.matchdata;

import org.ncfrcteams.frcscoutinghub2016.matchdata.schedule.MatchDescriptor;

/**
 * Created by pavan on 3/30/16.
 */
public class Match {

    public int matchnum;
    public int matchStatus;
    public MatchDescriptor matchDescriptor;

    public static Match newMatch(int matchnum, int matchStatus, MatchDescriptor matchDescriptor){
        Match match = new Match();
        match.matchnum = matchnum;
        match.matchStatus = matchStatus;
        match.matchDescriptor = matchDescriptor;
        return match;
    }

    public String getText(){
        return "Match " + this.matchnum;
    }

    public int getColor(){
        switch(matchStatus){
            case 0:
                return 0xff0000; //red = incomplete
            case 1:
                return 0x00ff00; //green = ready for scouting
            case 2:
                return 0xffff00; //yellow = scouting in progress
            default:
                return 0xffffff; //black = done (all data back)
        }
    }

}
