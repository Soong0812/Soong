package memo.controller;

import lombok.RequiredArgsConstructor;
import memo.dto.MemoRequestDto;
import memo.dto.MemoResponseDto;
import memo.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memo")
    public MemoResponseDto createMemo(
            @RequestBody MemoRequestDto memoRequestDto
    ) {
        return memoService.createMemo(memoRequestDto);
    }

    @GetMapping("/memo")
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();
    }

    @GetMapping("/memo/{memoId}")
    public MemoResponseDto getMemo(
            @PathVariable Long memoId
    ) {
        return memoService.getMemo(memoId);
    }

    @PutMapping("/memo/{memoId}")
    public MemoResponseDto updateMemo(
            @PathVariable Long memoId,
            @RequestBody MemoRequestDto memoRequestDto
    ) {
        return memoService.updateMemo(memoId, memoRequestDto);
    }

    @DeleteMapping("/memo/{memoId}")
    public void deleteMemo(
            @PathVariable Long memoId
    ) {
        memoService.deleteMemo(memoId);
    }
}
