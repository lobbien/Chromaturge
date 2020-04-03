package TheChromaturge.cards;

import TheChromaturge.DefaultMod;
import TheChromaturge.actions.CallWispAction;
import TheChromaturge.characters.TheDefault;
import TheChromaturge.orbs.*;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static TheChromaturge.DefaultMod.makeCardPath;

public class OrbSkill extends CustomCard {

    /*
     * Orb time.
     *
     * Channel 1 Default Orb.
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID("OrbSkill");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Skill.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    // /STAT DECLARATION/

    public OrbSkill() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new CallWispAction(new RedWisp(3)));
        AbstractDungeon.actionManager.addToBottom(new CallWispAction(new DarkWisp(3)));
        AbstractDungeon.actionManager.addToBottom(new CallWispAction(new BlueWisp(2)));
        AbstractDungeon.actionManager.addToBottom(new CallWispAction(new YellowWisp(3)));
        AbstractDungeon.actionManager.addToBottom(new CallWispAction(new GreenWisp(3)));
        AbstractDungeon.actionManager.addToBottom(new CallWispAction(new RedWisp(3)));
        AbstractDungeon.actionManager.addToBottom(new CallWispAction(new PurpleWisp(3)));
        AbstractDungeon.actionManager.addToBottom(new CallWispAction(new OrangeWisp(4)));
        AbstractDungeon.actionManager.addToBottom(new CallWispAction(new GrayWisp(4)));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.initializeDescription();
        }
    }
}