package TheChromaturge;

import TheChromaturge.cards.*;
import TheChromaturge.characters.TheDefault;
import TheChromaturge.characters.TheDefault.Enums;
import TheChromaturge.events.IdentityCrisisEvent;
import TheChromaturge.potions.PlaceholderPotion;
import TheChromaturge.relics.BottledPlaceholderRelic;
import TheChromaturge.relics.DefaultClickableRelic;
import TheChromaturge.relics.PlaceholderRelic;
import TheChromaturge.relics.PlaceholderRelic2;
import TheChromaturge.util.IDCheckDontTouchPls;
import TheChromaturge.util.TextureLoader;
import TheChromaturge.variables.DefaultCustomVariable;
import TheChromaturge.variables.DefaultSecondMagicNumber;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


@SpireInitializer
public class DefaultMod implements EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, EditCharactersSubscriber, PostInitializeSubscriber {
    public static final Logger logger = LogManager.getLogger(DefaultMod.class.getName());
    private static String modID;
    public static Properties theDefaultDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true;
    private static final String MODNAME = "Chromaturge";
    private static final String AUTHOR = "Lobbienjonsji";
    private static final String DESCRIPTION = "No Description yet.";
    public static final Color DEFAULT_GRAY = CardHelper.getColor(64.0F, 70.0F, 70.0F);
    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0F, 53.0F, 18.0F);
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0F, 230.0F, 230.0F);
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0F, 25.0F, 10.0F);
    private static final String ATTACK_DEFAULT_GRAY = "TheChromaturgeResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY = "TheChromaturgeResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY = "TheChromaturgeResources/images/512/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY = "TheChromaturgeResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "TheChromaturgeResources/images/512/card_small_orb.png";
    private static final String ATTACK_DEFAULT_GRAY_PORTRAIT = "TheChromaturgeResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY_PORTRAIT = "TheChromaturgeResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY_PORTRAIT = "TheChromaturgeResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY_PORTRAIT = "TheChromaturgeResources/images/1024/card_default_gray_orb.png";
    public static SpriteBatch Wispbatch;
    private static final String THE_DEFAULT_BUTTON = "TheChromaturgeResources/images/charSelect/DefaultCharacterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "TheChromaturgeResources/images/charSelect/DefaultCharacterPortraitBG.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "TheChromaturgeResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "TheChromaturgeResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "TheChromaturgeResources/images/char/defaultCharacter/corpse.png";
    public static final String BADGE_IMAGE = "TheChromaturgeResources/images/Badge.png";
    public static final String THE_DEFAULT_SKELETON_ATLAS = "TheChromaturgeResources/images/char/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "TheChromaturgeResources/images/char/defaultCharacter/skeleton.json";

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/images/orbs/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }

    public DefaultMod() {
        logger.info("Subscribe to BaseMod hooks");
        BaseMod.subscribe(this);
        setModID("TheChromaturge");
        logger.info("Done subscribing");
        logger.info("Creating the color " + Enums.COLOR_GRAY.toString());
        BaseMod.addColor(Enums.COLOR_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, "TheChromaturgeResources/images/512/bg_attack_default_gray.png", "TheChromaturgeResources/images/512/bg_skill_default_gray.png", "TheChromaturgeResources/images/512/bg_power_default_gray.png", "TheChromaturgeResources/images/512/card_default_gray_orb.png", "TheChromaturgeResources/images/1024/bg_attack_default_gray.png", "TheChromaturgeResources/images/1024/bg_skill_default_gray.png", "TheChromaturgeResources/images/1024/bg_power_default_gray.png", "TheChromaturgeResources/images/1024/card_default_gray_orb.png", "TheChromaturgeResources/images/512/card_small_orb.png");
        logger.info("Done creating the color");
        logger.info("Adding mod settings");
        theDefaultDefaultSettings.setProperty("enablePlaceholder", "FALSE");

        try {
            SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings);
            config.load();
            enablePlaceholder = config.getBool("enablePlaceholder");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        logger.info("Done adding mod settings");
    }

    public static void setModID(String ID) {
        Gson coolG = new Gson();
        InputStream in = DefaultMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = (IDCheckDontTouchPls)coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        logger.info("You are attempting to set your mod ID as: " + ID);
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) {
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        } else {
            if (ID.equals(EXCEPTION_STRINGS.DEVID)) {
                modID = EXCEPTION_STRINGS.DEFAULTID;
            } else {
                modID = ID;
            }

            logger.info("Success! ID is " + modID);
        }
    }

    public static String getModID() {
        return modID;
    }

    private static void pathCheck() {
        Gson coolG = new Gson();
        InputStream in = DefaultMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = (IDCheckDontTouchPls)coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = DefaultMod.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) {
            if (!packageName.equals(getModID())) {
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
            }

            if (!resourcePathExists.exists()) {
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
            }
        }

    }

    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        new DefaultMod();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }

    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. Add " + Enums.THE_DEFAULT.toString());
        BaseMod.addCharacter(new TheDefault("the Default", Enums.THE_DEFAULT), "TheChromaturgeResources/images/charSelect/DefaultCharacterButton.png", "TheChromaturgeResources/images/charSelect/DefaultCharacterPortraitBG.png", Enums.THE_DEFAULT);
        this.receiveEditPotions();
        logger.info("Added " + Enums.THE_DEFAULT.toString());
    }

    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        Texture badgeTexture = TextureLoader.getTexture("TheChromaturgeResources/images/Badge.png");
        ModPanel settingsPanel = new ModPanel();
        Wispbatch = new SpriteBatch();
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is the text which goes next to the checkbox.", 350.0F, 700.0F, Settings.CREAM_COLOR, FontHelper.charDescFont, enablePlaceholder, settingsPanel, (label) -> {
        }, (button) -> {
            enablePlaceholder = button.enabled;

            try {
                SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings);
                config.setBool("enablePlaceholder", enablePlaceholder);
                config.save();
            } catch (Exception var2) {
                var2.printStackTrace();
            }

        });
        settingsPanel.addUIElement(enableNormalsButton);
        BaseMod.registerModBadge(badgeTexture, "Chromaturge", "Lobbienjonsji", "No Description yet.", settingsPanel);
        BaseMod.addEvent(IdentityCrisisEvent.ID, IdentityCrisisEvent.class, "TheCity");
        logger.info("Done loading badge Image and mod options");
    }

    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");
        BaseMod.addPotion(PlaceholderPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, PlaceholderPotion.POTION_ID, Enums.THE_DEFAULT);
        logger.info("Done editing potions");
    }

    public void receiveEditRelics() {
        logger.info("Adding relics");
        BaseMod.addRelicToCustomPool(new PlaceholderRelic(), Enums.COLOR_GRAY);
        BaseMod.addRelicToCustomPool(new BottledPlaceholderRelic(), Enums.COLOR_GRAY);
        BaseMod.addRelicToCustomPool(new DefaultClickableRelic(), Enums.COLOR_GRAY);
        BaseMod.addRelic(new PlaceholderRelic2(), RelicType.SHARED);
        UnlockTracker.markRelicAsSeen(BottledPlaceholderRelic.ID);
        logger.info("Done adding relics!");
    }

    public void receiveEditCards() {
        logger.info("Adding variables");
        pathCheck();
        logger.info("Add variabls");
        BaseMod.addDynamicVariable(new DefaultCustomVariable());
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());
        logger.info("Adding cards");
        BaseMod.addCard(new OrbSkill());
        BaseMod.addCard(new DefaultSecondMagicNumberSkill());
        BaseMod.addCard(new DefaultCommonAttack());
        BaseMod.addCard(new DefaultAttackWithVariable());
        BaseMod.addCard(new DefaultCommonSkill());
        BaseMod.addCard(new DefaultCommonPower());
        BaseMod.addCard(new DefaultUncommonSkill());
        BaseMod.addCard(new DefaultUncommonAttack());
        BaseMod.addCard(new DefaultUncommonPower());
        BaseMod.addCard(new DefaultRareAttack());
        BaseMod.addCard(new DefaultRareSkill());
        BaseMod.addCard(new DefaultRarePower());
        logger.info("Making sure the cards are unlocked.");
        UnlockTracker.unlockCard(OrbSkill.ID);
        UnlockTracker.unlockCard(DefaultSecondMagicNumberSkill.ID);
        UnlockTracker.unlockCard(DefaultCommonAttack.ID);
        UnlockTracker.unlockCard(DefaultAttackWithVariable.ID);
        UnlockTracker.unlockCard(DefaultCommonSkill.ID);
        UnlockTracker.unlockCard(DefaultCommonPower.ID);
        UnlockTracker.unlockCard(DefaultUncommonSkill.ID);
        UnlockTracker.unlockCard(DefaultUncommonAttack.ID);
        UnlockTracker.unlockCard(DefaultUncommonPower.ID);
        UnlockTracker.unlockCard(DefaultRareAttack.ID);
        UnlockTracker.unlockCard(DefaultRareSkill.ID);
        UnlockTracker.unlockCard(DefaultRarePower.ID);
        logger.info("Done adding cards!");
    }

    public void receiveEditStrings() {
        logger.info("You seeing this?");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());
        BaseMod.loadCustomStringsFile(CardStrings.class, getModID() + "Resources/localization/eng/DefaultMod-Card-Strings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, getModID() + "Resources/localization/eng/DefaultMod-Power-Strings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, getModID() + "Resources/localization/eng/DefaultMod-Relic-Strings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, getModID() + "Resources/localization/eng/DefaultMod-Event-Strings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, getModID() + "Resources/localization/eng/DefaultMod-Potion-Strings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, getModID() + "Resources/localization/eng/DefaultMod-Character-Strings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, getModID() + "Resources/localization/eng/DefaultMod-Orb-Strings.json");
        logger.info("Done edittting strings");
    }

    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/DefaultMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = (Keyword[])gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            Keyword[] var4 = keywords;
            int var5 = keywords.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Keyword keyword = var4[var6];
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }

    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
