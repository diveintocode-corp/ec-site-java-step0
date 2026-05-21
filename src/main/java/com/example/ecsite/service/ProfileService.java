package com.example.ecsite.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecsite.entity.Profile;
import com.example.ecsite.form.ProfileForm;
import com.example.ecsite.repository.ProfileRepository;

/**
 * プロフィール関連のビジネスロジックを提供するサービスクラス。
 */
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * ユーザーのプロフィールを取得する。存在しない場合は null を返す。
     */
    public Profile findByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    /**
     * プロフィールを保存する。既存の場合は更新、なければ新規作成する。
     */
    @Transactional
    public void saveProfile(Long userId, ProfileForm form) {
        Profile existing = profileRepository.findByUserId(userId);
        if (existing != null) {
            existing.setName(form.getName());
            existing.setPostalCode(form.getPostalCode());
            existing.setPrefecture(form.getPrefecture());
            existing.setAddress1(form.getAddress1());
            existing.setAddress2(form.getAddress2());
            existing.setTelno(form.getTelno());
            profileRepository.update(existing);
        } else {
            Profile profile = new Profile();
            profile.setUserId(userId);
            profile.setName(form.getName());
            profile.setPostalCode(form.getPostalCode());
            profile.setPrefecture(form.getPrefecture());
            profile.setAddress1(form.getAddress1());
            profile.setAddress2(form.getAddress2());
            profile.setTelno(form.getTelno());
            profileRepository.insert(profile);
        }
    }

    /**
     * プロフィールを更新する（管理者用）。
     */
    @Transactional
    public void updateProfile(Profile profile) {
        profileRepository.update(profile);
    }
}
