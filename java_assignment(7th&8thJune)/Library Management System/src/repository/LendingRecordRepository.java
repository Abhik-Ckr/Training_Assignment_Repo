package repository;

import model.LendingRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LendingRecordRepository extends Repository<LendingRecord>{
    public List<LendingRecord> findByMemberId(String memberId) {
        return getAll().stream()
                .filter(record -> record.getMember().getMemberId().equals(memberId))
                .collect(Collectors.toList());
    }

    public List<LendingRecord> findActiveLendings() {
        return getAll().stream()
                .filter(record -> record.getReturnDate() == null)
                .collect(Collectors.toList());
    }
    public List<LendingRecord> findOverdueRecords() {
        return getAll().stream()
                .filter(record -> record.getReturnDate() == null &&
                        record.getDueDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }
}
