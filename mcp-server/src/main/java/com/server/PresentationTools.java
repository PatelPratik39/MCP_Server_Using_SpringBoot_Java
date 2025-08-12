package com.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PresentationTools {

    private List<Presentation> presentations = new ArrayList<>();

        public PresentationTools() {
            var keynoteOne = new Presentation(
                    "Java 24 Launch - Live from JavaOne 2025",
                    "https://www.youtube.com/watch?v=m4h3j2v",2025
            );
            var keynoteTwo = new Presentation(
                    "Java Turns 30 - Live from JavaOne 2025",
                    "https://www.youtube.com/watch?v=Gw23m4h",2025
            );
            var concerto = new Presentation(
                    "Concerto for Java & AI - Building Production-Ready LLM Applications",
                    "https://www.youtube.com/watch?v=9jdsl2f",2024
            );
            var gatherers = new Presentation(
                    "Stream Gatherers - Deep Dive with the Expert",
                    "https://www.youtube.com/watch?v=f82m5sa",2023
            );
            var ai202 = new Presentation(
                    "AI 202: Next-Level AI Mastery for Java Developers",
                    "https://www.youtube.com/watch?v=7dks93f",2022
            );
            var sequenced = new Presentation("Sequenced Collections - Deep Dive with the Expert", "https://www.youtube.com/watch?v=d82ks9s",2021);

            this.presentations.addAll(List.of(
                    keynoteOne, keynoteTwo, concerto, gatherers, ai202, sequenced
            ));
    }

    public List<Presentation> getPresentations() {
            return presentations;
    }

    public List<Presentation> getPresentationsByYear(int year) {
            return presentations.stream().filter(p -> p.year() == year).toList();
    }

    public List<Map<String, Object>> getPresentationsAsMapList(){
            return presentations.stream()
                    .map(p -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("title", p.title());
                        map.put("url", p.url());
                        map.put("year", p.year());
                        return map;
                    })
                    .collect(Collectors.toList());
    }
}
