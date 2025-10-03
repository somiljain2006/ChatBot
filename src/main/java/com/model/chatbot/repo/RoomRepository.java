package com.model.chatbot.repo;

import com.model.chatbot.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoomRepository extends JpaRepository<Room, String> {
}
