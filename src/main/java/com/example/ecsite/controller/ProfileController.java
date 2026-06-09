package com.example.ecsite.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecsite.entity.Profile;
import com.example.ecsite.form.ProfileForm;
import com.example.ecsite.security.CustomUserDetails;
import com.example.ecsite.service.ProfileService;

/**
 * ユーザープロフィール編集コントローラ。
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * プロフィール編集画面を表示する。
     * 既存のプロフィールがあれば初期値としてセットする。
     */
    @GetMapping("/edit")
    public String editProfile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Profile data = profileService.findByUserId(userDetails.getUserId());
        ProfileForm form = new ProfileForm();
        if (data != null) {
            form.setName(data.getName());
            form.setPostalCode(data.getPostalCode());
            form.setPrefecture(data.getPrefecture());
            form.setAddress1(data.getAddress1());
            form.setAddress2(data.getAddress2());
            form.setTelno(data.getTelno());
        }
        model.addAttribute("profileForm", form);
        model.addAttribute("prefectures", PREFECTURE_LIST);
        return "profile/edit";
    }

    /**
     * プロフィールを保存する。
     * 成功時は商品一覧画面にリダイレクトする。
     */
    @PostMapping("/edit")
    public String saveProfile(@ModelAttribute ProfileForm input,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model) {
        String msg = input.validate();
        if (msg != null) {
            model.addAttribute("errorMessage", msg);
            model.addAttribute("prefectures", PREFECTURE_LIST);
            return "profile/edit";
        }
        profileService.saveProfile(userDetails.getUserId(), input);
        return "redirect:/products";
    }

    public static final String[] PREFECTURE_LIST = {
            "北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県",
            "茨城県", "栃木県", "群馬県", "埼玉県", "千葉県", "東京都", "神奈川県",
            "新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県", "岐阜県",
            "静岡県", "愛知県", "三重県", "滋賀県", "京都府", "大阪府", "兵庫県",
            "奈良県", "和歌山県", "鳥取県", "島根県", "岡山県", "広島県", "山口県",
            "徳島県", "香川県", "愛媛県", "高知県", "福岡県", "佐賀県", "長崎県",
            "熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県"
    };
}
