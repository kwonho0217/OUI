package com.emotionoui.oui.diary.service;

import com.emotionoui.oui.diary.dto.req.CreateDailyDiaryReq;
import com.emotionoui.oui.diary.dto.req.UpdateDailyDiaryReq;
import com.emotionoui.oui.diary.dto.res.SearchDailyDiaryRes;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface DiaryService {
    String createDailyDiary(CreateDailyDiaryReq req) throws IOException, ExecutionException, InterruptedException;
    Integer updateDailyDiary(UpdateDailyDiaryReq req, Integer dailyId);
    void deleteDailyDiary(String dailyId);
    SearchDailyDiaryRes searchDailyDiary(Integer dailyId);
//    EmotionClass searchEmotion(String dailyId);
//    MusicClass searchMusic(String dailyId);
//    String searchComment(String dailyId);
}