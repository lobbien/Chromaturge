package TheChromaturge.orbs;

import TheChromaturge.DefaultMod;
import TheChromaturge.powers.MagnifyPower;
import TheChromaturge.util.TextureLoader;
import TheChromaturge.vfx.YellowWispParticleEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import org.lwjgl.opengl.GL14;

public class YellowWisp extends AbstractWisp {
    private static final Texture IMG = TextureLoader.getTexture(DefaultMod.makeOrbPath("base_wisp_2.png"));
    private static final Texture SQUARE = TextureLoader.getTexture(DefaultMod.makeOrbPath("white_square.png"));
    private static final Texture FLARES1 = TextureLoader.getTexture(DefaultMod.makeOrbPath("wisp_flares1.png"));
    private static final Texture FLARES2 = TextureLoader.getTexture(DefaultMod.makeOrbPath("wisp_flares2.png"));
    private static final Texture Core = TextureLoader.getTexture(DefaultMod.makeOrbPath("wisp_core.png"));
    private float vfxTimer = 1.0F;
    private float vfxIntervalMin = 0.05F;
    private float vfxIntervalMax = 0.2F;
    private static final float ORB_WAVY_DIST = 0.04F;
    public static final String ORB_ID = DefaultMod.makeID("YellowWisp");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    public YellowWisp(int duration) {
        super(duration);
        this.img = IMG;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + passiveAmount + DESC[1];
    }

    public void onEvoke() {
    }

    public AbstractOrb makeCopy() {
        return new YellowWisp(this.baseEvokeAmount);
    }

    @Override
    public void onCallOther() {
        int i = 1;
        if (AbstractDungeon.player.hasPower(MagnifyPower.POWER_ID)) {
            i += AbstractDungeon.player.getPower(MagnifyPower.POWER_ID).amount;
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(i, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void update() {
        super.update();
        int i = 0;
        if (AbstractDungeon.player.hasPower(MagnifyPower.POWER_ID)) {
            i += AbstractDungeon.player.getPower(MagnifyPower.POWER_ID).amount;
        }
        basePassiveAmount = i;
        passiveAmount = basePassiveAmount;
        this.updateDescription();
    }

    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 45.0F;
        this.vfxTimer -= Gdx.graphics.getDeltaTime();
        if (this.vfxTimer < 0.0F) {
            AbstractDungeon.effectList.add(new YellowWispParticleEffect(this.cX, this.cY+ this.bobEffect.y));
            this.vfxTimer = MathUtils.random(this.vfxIntervalMin, this.vfxIntervalMax);
        }

    }

    public void render(SpriteBatch sb) {
        sb.end();
        DefaultMod.Wispbatch.begin();
        Gdx.gl.glColorMask(false, false, false, true);
        DefaultMod.Wispbatch.setBlendFunction(1, 0);
        DefaultMod.Wispbatch.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale,-this.angle, 0, 0, 96, 96, false, false);
        DefaultMod.Wispbatch.flush();
        GL14.glBlendEquation(32776);
        DefaultMod.Wispbatch.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, -this.angle / 2.0F, 0, 0, 96, 96, false, false);
        DefaultMod.Wispbatch.flush();
        GL14.glBlendEquation(32774);
        DefaultMod.Wispbatch.setBlendFunction(772, 773);
        Gdx.gl.glColorMask(true, true, true, true);
        DefaultMod.Wispbatch.setColor(new Color(0.8F, 0.8F, 0.0F, 1.0F));
        DefaultMod.Wispbatch.draw(SQUARE, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale,-this.angle, 0, 0, 96, 96, false, false);
        DefaultMod.Wispbatch.setBlendFunction(770, 771);
        DefaultMod.Wispbatch.setColor(new Color(1.0F, 0.7F, 0.5F, 0.4F));
        DefaultMod.Wispbatch.draw(FLARES1, this.cX - 40.0F, this.cY - 40.0F + this.bobEffect.y, 40.0F, 40.0F, 80.0F, 80.0F, this.scale, this.scale, - this.angle * 2, 0, 0, 96, 96, false, false);
        DefaultMod.Wispbatch.draw(FLARES2, this.cX - 40.0F, this.cY - 40.0F + this.bobEffect.y, 40.0F, 40.0F, 80.0F, 80.0F, this.scale, this.scale, - this.angle * 1.5f, 0, 0, 96, 96, false, false);
        DefaultMod.Wispbatch.setBlendFunction(770, 1);
        DefaultMod.Wispbatch.setColor(new Color(1.0F, 0.5F, 0.2F, 0.6F));
        DefaultMod.Wispbatch.draw(Core, this.cX - 40.0F, this.cY - 40.0F + this.bobEffect.y, 40.0F, 40.0F, 80.0F, 80.0F, this.scale, this.scale, this.angle * 0.5f, 0, 0, 96, 96, false, false);
        DefaultMod.Wispbatch.draw(Core, this.cX - 40.0F, this.cY - 40.0F + this.bobEffect.y, 40.0F, 40.0F, 80.0F, 80.0F, this.scale, this.scale, -this.angle * 0.5f, 0, 0, 96, 96, false, false);
        DefaultMod.Wispbatch.end();
        sb.begin();
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.passiveAmount), this.cX - NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, this.c, this.fontScale);
        this.renderText(sb);
        this.hb.render(sb);
    }

    public void playChannelSFX() {
    }
}
