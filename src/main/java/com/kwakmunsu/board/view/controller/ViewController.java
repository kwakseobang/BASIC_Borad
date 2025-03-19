package com.kwakmunsu.board.view.controller;

import com.kwakmunsu.board.view.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ViewController {

    private final ViewService viewService;

}