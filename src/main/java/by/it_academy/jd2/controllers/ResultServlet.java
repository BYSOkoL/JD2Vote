package by.it_academy.jd2.controllers;

import by.it_academy.jd2.dto.SavedVoteDTO;
import by.it_academy.jd2.dto.VoteResultDTO;
import by.it_academy.jd2.services.ResultService;
import by.it_academy.jd2.services.singletone.ResultServiceSingleton;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ResultServlet", urlPatterns = "/vote_result")
public class ResultServlet extends HttpServlet {
    private static final String HEADER_SINGER_RESULT = "<p>Total singer votes:</p>";
    private static final String HEADER_STYLES_RESULT = "<p>Total styles votes:</p>";
    private static final String HEADER_MESSAGE = "<p>Message:</p>";
    private static final String BR = "<br>";

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.LLL HH:mm:ss");
    private final ResultService resultService = ResultServiceSingleton.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        VoteResultDTO voteResult = resultService.getVoteResult();

        Map<String, Long> singers = voteResult.getSortedSingerVotes();
        writer.write(HEADER_SINGER_RESULT);
        for (Map.Entry<String, Long> singer : singers.entrySet()) {
            writer.write("<p>" + singer.getKey() + "  " + singer.getValue() + "</p>");
        }

        Map<String, Long> styles = voteResult.getSortedStyleVotes();
        writer.write(BR + HEADER_STYLES_RESULT);
        for (Map.Entry<String, Long> style : styles.entrySet()) {
            writer.write("<p>" + style.getKey() + "  " + style.getValue() + "</p>");
        }

        List<SavedVoteDTO> sortedVotes = voteResult.getSortedVoteInfos();
        writer.write(BR + HEADER_MESSAGE);
        for (SavedVoteDTO savedVote : sortedVotes) {
            writer.write("<p>" + savedVote.getCreateDateTime().format(FORMAT) + "  " + savedVote.getVote().getMessage() + "</p>");
        }
    }
}
