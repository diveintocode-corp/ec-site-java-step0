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
        Profile data = profileRepository.findByUserId(userId);
        if (data != null) {
            data.setName(form.getName());
            data.setPostalCode(form.getPostalCode());
            data.setPrefecture(form.getPrefecture());
            data.setAddress1(form.getAddress1());
            data.setAddress2(form.getAddress2());
            data.setTelno(form.getTelno());
            profileRepository.update(data);
        } else {
            Profile p = new Profile();
            p.setUserId(userId);
            p.setName(form.getName());
            p.setPostalCode(form.getPostalCode());
            p.setPrefecture(form.getPrefecture());
            p.setAddress1(form.getAddress1());
            p.setAddress2(form.getAddress2());
            p.setTelno(form.getTelno());
            profileRepository.insert(p);
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
