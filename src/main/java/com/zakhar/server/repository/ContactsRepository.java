package com.zakhar.server.repository;


import com.zakhar.server.entity.Contacts;
import com.zakhar.server.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Users> {


}
