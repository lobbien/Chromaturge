package TheChromaturge.orbs;

import TheChromaturge.DefaultMod;
import TheChromaturge.actions.DarkWispCallAction;
import TheChromaturge.util.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import org.lwjgl.opengl.GL14;

public class DarkWisp extends AbstractWisp {
    private static final Texture IMG = TextureLoader.getTexture(DefaultMod.makeOrbPath("base_wisp_2.png"));
    private static final Texture SQUARE = TextureLoader.getTexture(DefaultMod.makeOrbPath("dark_wisp_overlay.png"));
    private static final Texture FLARES1 = TextureLoader.getTexture(DefaultMod.makeOrbPath("wisp_flares1.png"));
    private static final Texture FLARES2 = TextureLoader.getTexture(DefaultMod.makeOrbPath("wisp_flares2.png"));
    private static final Texture Core = TextureLoader.getTexture(DefaultMod.makeOrbPath("wisp_core.png"));

    public static final String ORB_ID = DefaultMod.makeID("DarkWisp");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;
    private float vfxTimer = 1.0F;
    private float vfxIntervalMin = 1.0F;
    private float vfxIntervalMax = 2.0F;
    private static final float ORB_WAVY_DIST = 0.04F;

    public DarkWisp(int duration) {
        super(duration);
        this.img = IMG;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESC[0];
    }

    public void onEvoke() {
    }


    @Override
    public void onCall() {
        AbstractDungeon.actionManager.addToTop(new DarkWispCallAction(this));
    }

    public AbstractOrb makeCopy() {
        return new DarkWisp(this.baseEvokeAmount);
    }


    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 45.0F;
    }


    public void render(SpriteBatch sb) {
        sb.end();
        DefaultMod.Wispbatch.begin();
        Gdx.gl.glColorMask(false, false, false, true);
        DefaultMod.Wispbatch.setBlendFunction(1, 0);
        DefaultMod.Wispbatch.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, -this.angle, 0, 0, 96, 96, false, false);
        DefaultMod.Wispbatch.flush();
        GL14.glBlendEquation(32776);
        DefaultMod.Wispbatch.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, -this.angle / 2.0F, 0, 0, 96, 96, false, false);
        DefaultMod.Wispbatch.flush();
        GL14.glBlendEquation(32774);
        DefaultMod.Wispbatch.setBlendFunction(772, 773);
        Gdx.gl.glColorMask(true, true, true, true);
        DefaultMod.Wispbatch.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
        DefaultMod.Wispbatch.draw(SQUARE, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, -this.angle, 0, 0, 96, 96, false, false);
        DefaultMod.Wispbatch.setBlendFunction(770, 771);
        DefaultMod.Wispbatch.setColor(new Color(0.0F, 0.5F, 1.0F, 0.4F));
        DefaultMod.Wispbatch.setBlendFunction(770, 1);
        DefaultMod.Wispbatch.end();
        sb.begin();
        this.renderText(sb);
        this.hb.render(sb);
    }

    public void playChannelSFX() {
    }
}
