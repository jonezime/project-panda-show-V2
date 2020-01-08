package com.wildcodeschool.pandashow.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildcodeschool.pandashow.entity.Episode;
import com.wildcodeschool.pandashow.entity.TvShow;
import com.wildcodeschool.pandashow.entity.User;
import com.wildcodeschool.pandashow.repository.EpisodeRepository;
import com.wildcodeschool.pandashow.repository.TvShowRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TvShowController {

    private EpisodeRepository episodeRepository = new EpisodeRepository();
    private TvShowRepository tvShowRepository = new TvShowRepository();

    @GetMapping("/discover-shows")
    public String discoverShows(Model model) {

        List<TvShow> shows = tvShowRepository.findAllTvShow();
        model.addAttribute("showList", shows);

        return "discover-shows.html";
    }

    @GetMapping("/show-details")
    public String showDetails(HttpSession session, Model out,
                              @RequestParam Long id,
                              @RequestParam (defaultValue = "1") Long idSeason) {

        TvShow show = tvShowRepository.findShowById(id);
        out.addAttribute("showDetails", show);

        List<Episode> episodes = episodeRepository.findAllEpisodesFromSeason(idSeason);
        out.addAttribute("seasonDetails", episodes);

        if (session.getAttribute("currentUser") != null) {

            return "show-user.html";
        }
        return "show-visitor.html";
    }

    @GetMapping("/remove-show")
    public String removeShow(HttpSession session,
                             @RequestParam Long idShow) {

        User user = (User) session.getAttribute("currentUser");
        tvShowRepository.deleteShowById(user.getId(), idShow);
        return "redirect:/mylist";
    }

    @GetMapping("/test/api")
    @ResponseBody
    public TvShow callApiTest() {

        WebClient webClient = WebClient.create("https://api.betaseries.com/shows/random?key=49579304f096");
        Mono<String> call = webClient.get()
                .retrieve()
                .bodyToMono(String.class);

        String response = call.block();

        ObjectMapper objectMapper = new ObjectMapper();
        TvShow tvShow;

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode shows = root.get("shows");
            for (JsonNode show : shows) {
                Long idApi = show.get("id").asLong();
                JsonNode images = show.get("images");
                String posterImg = images.get("poster").asText();
                String showImg = images.get("show").asText();
                String title = show.get("title").asText();
                String genre = null;

                for (JsonNode jsonNode : show.get("genres")) {
                    genre = jsonNode.asText();
                    break;
                }

                int releaseYear = show.get("creation").asInt();
                String summary = show.get("description").asText();

                tvShow = new TvShow(1L, idApi, posterImg, showImg, title, genre, releaseYear, summary);

                return tvShow;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}