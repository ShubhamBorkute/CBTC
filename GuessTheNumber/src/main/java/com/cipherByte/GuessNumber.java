package com.cipherByte;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/result")
public class GuessNumber extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final int ATTEMPT_LIMITS = 5;
    private static final String ORG_NUMBER = "orgNumber";
    private static final String ATTEMPTS = "attempts";
    private static final String ROUNDS = "rounds";
    private static final String TOTAL_SCORE = "totalScore";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orgNumber;
        int attempts;
        int rounds;
        int totalScore;

        if (req.getSession().getAttribute(ORG_NUMBER) == null) {
            initializeGame(req);
        }

        orgNumber = (int) req.getSession().getAttribute(ORG_NUMBER);
        attempts = (int) req.getSession().getAttribute(ATTEMPTS);
        rounds = (int) req.getSession().getAttribute(ROUNDS);
        totalScore = (int) req.getSession().getAttribute(TOTAL_SCORE);

        int num = Integer.parseInt(req.getParameter("num"));
        attempts++;

        if (num == orgNumber) {
            req.setAttribute("msg", "Congratulations! You guessed the number in " + attempts + " attempts.");
            totalScore++;
            rounds++;
            resetGame(req);
        } else if (attempts >= ATTEMPT_LIMITS) {
            req.setAttribute("msg", "Sorry, no attempts left. The number was " + orgNumber + ".");
            rounds++;
            resetGame(req);
        } else {
            if (num < orgNumber) {
                req.setAttribute("msg", "Too low!(Enter high) Try again."+attempts);
            } else {
                req.setAttribute("msg", "Too high!(Enter Low) Try again."+attempts);
            }
            req.getSession().setAttribute(ATTEMPTS, attempts);
        }

        req.getSession().setAttribute(ROUNDS, rounds);
        req.getSession().setAttribute(TOTAL_SCORE, totalScore);

        req.setAttribute("totalScore", totalScore);
        req.setAttribute("rounds", rounds);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    private void initializeGame(HttpServletRequest req) {
        Random obj = new Random();
        int orgNumber = obj.nextInt(100);
        req.getSession().setAttribute(ORG_NUMBER, orgNumber);
        req.getSession().setAttribute(ATTEMPTS, 0);
        req.getSession().setAttribute(ROUNDS, 0);
        req.getSession().setAttribute(TOTAL_SCORE, 0);
    }

    private void resetGame(HttpServletRequest req) {
        Random obj = new Random();
        int newOrgNumber = obj.nextInt(100);
        req.getSession().setAttribute(ORG_NUMBER, newOrgNumber);
        req.getSession().setAttribute(ATTEMPTS, 0);
    }
}
