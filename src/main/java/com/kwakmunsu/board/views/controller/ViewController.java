package com.kwakmunsu.board.views.controller;

import com.kwakmunsu.board.views.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ViewController {

    private final ViewService viewService;

}