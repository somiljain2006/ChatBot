package com.model.chatbot.repo;

import com.model.chatbot.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MessageRepository extends JpaRepository<Message, Long> {
  List<Message> findAllByRoomId(String roomId);
}
