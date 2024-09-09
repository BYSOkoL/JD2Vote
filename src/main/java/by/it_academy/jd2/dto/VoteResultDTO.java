package by.it_academy.jd2.dto;

import java.util.List;
import java.util.Map;

public class VoteResultDTO {
    private final Map<String, Long> sortedSingerVotes;
    private final Map<String, Long> sortedStyleVotes;
    private final List<SavedVoteDTO> sortedVote;

    public VoteResultDTO(Map<String, Long> sortedSingerVotes, Map<String, Long> sortedStyleVotes, List<SavedVoteDTO> sortedVote) {
        this.sortedSingerVotes = sortedSingerVotes;
        this.sortedStyleVotes = sortedStyleVotes;
        this.sortedVote = sortedVote;
    }

    public Map<String, Long> getSortedSingerVotes() {
        return sortedSingerVotes;
    }

    public Map<String, Long> getSortedStyleVotes() {
        return sortedStyleVotes;
    }

    public List<SavedVoteDTO> getSortedVoteInfos() {
        return sortedVote;
    }
}
