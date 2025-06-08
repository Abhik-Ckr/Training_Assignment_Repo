package repository;

import model.Book;
import model.Member;

import java.util.List;
import java.util.stream.Collectors;

public class MemberRepository extends Repository<Member> {
    public List<Member> findByName(String name){
        return getAll().stream().filter(member -> member.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }
    public List<Member> findByEmail(String email){
        return getAll().stream().filter(member -> member.getEmail().equalsIgnoreCase(email)).collect(Collectors.toList());
    }
}
