package TheChromaturge.vfx;

import TheChromaturge.DefaultMod;
import TheChromaturge.util.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.Random;

public class RedWispParticleEffect extends AbstractGameEffect {


    private static final Texture IMG1 = TextureLoader.getTexture(DefaultMod.makeVfxPath("RedWispParticle1.png"));
    private static final Texture IMG2 = TextureLoader.getTexture(DefaultMod.makeVfxPath("RedWispParticle2.png"));
    private static final Texture IMG3 = TextureLoader.getTexture(DefaultMod.makeVfxPath("RedWispParticle3.png"));
    private Texture img;
    private float posx;
    private float posy;
    private float Vfxtimer = 1.0f;
    public static Random r = new Random();
    private int speed;

    public RedWispParticleEffect(float posx, float posy)
    {
        this.startingDuration = 1.0f;
        this.duration = this.startingDuration;
        int roll = MathUtils.random(0, 2);
        if(roll == 0)
        {
            img = IMG1;
        }
        else if(roll == 1)
        {
            img = IMG2;
        }
        else
        {
            img = IMG3;
        }

        speed = MathUtils.random(15, 30);
        this.color = new Color(1.0f, r.nextFloat(), 0.0f, 0.6f);
        this.posx = posx - 30 * Settings.scale + r.nextFloat() * 60 * Settings.scale;
        this.posy = posy - 34 * Settings.scale + r.nextFloat() * 48 * Settings.scale;
    }

    @Override
    public void update() {
        color.a -=  Gdx.graphics.getDeltaTime() * 0.6f;
        Vfxtimer -= Gdx.graphics.getDeltaTime();
        posy += Gdx.graphics.getDeltaTime() * speed;
        if (Vfxtimer <= 0)
        {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(color);
        spriteBatch.draw(img, posx - 10*Settings.scale, posy, 20*Settings.scale, 20*Settings.scale);
        spriteBatch.setColor(Color.WHITE);
    }

    @Override
    public void dispose() {

    }
}
