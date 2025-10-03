package com.model.chatbot.controllers;

import com.model.chatbot.entity.Message;
import com.model.chatbot.repo.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

  private final MessageRepository messageRepository;

  @GetMapping("/history/{roomId}")
  public List<Message> history(@PathVariable String roomId) {
    return messageRepository.findAllByRoomId(roomId);
  }
}
