package memo.service;

import lombok.RequiredArgsConstructor;
import memo.dto.MemoRequestDto;
import memo.dto.MemoResponseDto;
import memo.entity.Memo;
import memo.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto createMemo(MemoRequestDto memoRequestDto) {
        Memo savedMemo = memoRepository.save(new Memo(memoRequestDto.getContent()));
        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    @Transactional(readOnly = true)
    public MemoResponseDto getMemo(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 없습니다.")
        );

        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemos() {
        List<Memo> memoList = memoRepository.findAll();
        List<MemoResponseDto> dtoList = new ArrayList<>();

        for (Memo memo : memoList) {
            dtoList.add(new MemoResponseDto(memo.getId(), memo.getContent()));
        }
        return dtoList;
    }

    @Transactional
    public MemoResponseDto updateMemo(Long id, MemoRequestDto memoRequestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 없습니다.")
        );

        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public void deleteMemo(Long id) {
        memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 없습니다.")
        );
        memoRepository.deleteById(id);
    }
}
