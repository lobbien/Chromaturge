package TheChromaturge.vfx;

import TheChromaturge.DefaultMod;
import TheChromaturge.util.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.Random;

public class BlueWispParticleEffect extends AbstractGameEffect {


    private static final Texture IMG1 = TextureLoader.getTexture(DefaultMod.makeVfxPath("BlueWispParticle.png"));
    private Texture img;
    private float posx;
    private float posy;
    private float Vfxtimer = 2.0f;
    public static Random r = new Random();
    private float scale;

    public BlueWispParticleEffect(float posx, float posy)
    {
        this.startingDuration = 1.0f;
        this.duration = this.startingDuration;
        img = IMG1;
        scale = 1;
        float y = r.nextFloat();
        this.color = new Color(y, y, 1.0f, 0.6f);
        this.posx = posx - 30 * Settings.scale + r.nextFloat() * 60 * Settings.scale;
        this.posy = posy - 30 * Settings.scale + r.nextFloat() * 60 * Settings.scale;
    }

    @Override
    public void update() {
        color.a -=  Gdx.graphics.getDeltaTime() * 0.6f;
        Vfxtimer -= Gdx.graphics.getDeltaTime();
        this.scale += Gdx.graphics.getDeltaTime() * 0.5;
        if (Vfxtimer <= 0)
        {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(color);
        spriteBatch.draw(img, posx - this.scale*Settings.scale * 10, posy- this.scale*Settings.scale * 10, 20 * Settings.scale * this.scale, 20*Settings.scale * this.scale);
        spriteBatch.setColor(Color.WHITE);
    }

    @Override
    public void dispose() {

    }
}
