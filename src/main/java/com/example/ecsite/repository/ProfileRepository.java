package com.example.ecsite.repository;

import org.springframework.stereotype.Repository;

import com.example.ecsite.entity.Profile;
import com.example.ecsite.mapper.ProfileMapper;

/**
 * プロフィールデータへのアクセスを提供するリポジトリクラス。
 */
@Repository
public class ProfileRepository {

    private final ProfileMapper profileMapper;

    public ProfileRepository(ProfileMapper profileMapper) {
        this.profileMapper = profileMapper;
    }

    public Profile findByUserId(Long userId) {
        return profileMapper.findByUserId(userId);
    }

    public void insert(Profile profile) {
        profileMapper.insert(profile);
    }

    public void update(Profile profile) {
        profileMapper.update(profile);
    }
}
