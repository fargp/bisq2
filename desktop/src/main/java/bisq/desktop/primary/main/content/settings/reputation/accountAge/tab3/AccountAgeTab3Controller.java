/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.desktop.primary.main.content.settings.reputation.accountAge.tab3;

import bisq.application.DefaultApplicationService;
import bisq.common.observable.Pin;
import bisq.desktop.common.Browser;
import bisq.desktop.common.observable.FxBindings;
import bisq.desktop.common.utils.ClipboardUtil;
import bisq.desktop.common.view.Controller;
import bisq.desktop.common.view.Navigation;
import bisq.desktop.common.view.NavigationTarget;
import bisq.desktop.primary.main.content.components.UserProfileSelection;
import bisq.desktop.primary.overlay.OverlayController;
import bisq.user.identity.UserIdentityService;
import bisq.user.reputation.AccountAgeService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountAgeTab3Controller implements Controller {

    private final AccountAgeTab3Model model;
    @Getter
    private final AccountAgeTab3View view;
    private final UserIdentityService userIdentityService;
    private final AccountAgeService accountAgeService;
    private Pin selectedUserProfilePin;

    public AccountAgeTab3Controller(DefaultApplicationService applicationService) {
        userIdentityService = applicationService.getUserService().getUserIdentityService();
        UserProfileSelection userProfileSelection = new UserProfileSelection(userIdentityService);
        accountAgeService = applicationService.getUserService().getReputationService().getAccountAgeService();

        model = new AccountAgeTab3Model();
        view = new AccountAgeTab3View(model, this, userProfileSelection.getRoot());
    }

    @Override
    public void onActivate() {
        selectedUserProfilePin = FxBindings.subscribe(userIdentityService.getSelectedUserProfile(),
                chatUserIdentity -> {
                    model.getSelectedChatUserIdentity().set(chatUserIdentity);
                    model.getPubKeyHash().set(chatUserIdentity.getId());
                }
        );
    }

    @Override
    public void onDeactivate() {
        selectedUserProfilePin.unbind();
    }

    void onBack() {
        Navigation.navigateTo(NavigationTarget.ACCOUNT_AGE_TAB_2);
    }

    void onLearnMore() {
        Browser.open("https://bisq.wiki/reputation/burnBsq");
    }

    void onCopyToClipboard(String pubKeyHash) {
        String prefix = "BISQ2_ACCOUNT_AGE_REQUEST:";
        ClipboardUtil.copyToClipboard(prefix + pubKeyHash);
    }

    void onClose() {
        OverlayController.hide();
    }

    public void onRequestCertificate() {
        accountAgeService.requestCertificate(ClipboardUtil.getClipboardString());
    }
}
