package com.model.chatbot.controllers;

import com.model.chatbot.dto.CreateRoomRequestDTO;
import com.model.chatbot.entity.Room;
import com.model.chatbot.repo.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

  private final RoomRepository roomRepository;

  @PostMapping
  public ResponseEntity<Room> createRoom(@RequestBody CreateRoomRequestDTO req) {
    String roomId = Stream.of(req.getUserA(), req.getUserB()).sorted().toString();
    Room room = roomRepository.findById(roomId).orElse(null);
    if (room == null) {
      room = roomRepository.save(new Room(roomId, req.getUserA(), req.getUserB()));
    }
    return ResponseEntity.ok(room);
  }

  @GetMapping("/{roomId}")
  public ResponseEntity<Room> getRoom(@PathVariable String roomId) {
    Room room = roomRepository.findById(roomId).orElse(null);
    if (room != null) {
      return ResponseEntity.ok(room);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}