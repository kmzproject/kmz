package ru.kmz.server.engine.gant;

public class DurationComplite {

	private int duration;
	private int complite;

	public DurationComplite() {
		this(0, 0);
	}

	public DurationComplite(int duration, int persents) {
		this.duration = duration;
		this.complite = (duration * persents) / 100;
	}

	public void add(DurationComplite dc) {
		duration += dc.duration;
		complite += dc.complite;
	}

	public int getPersent() {
		if (complite == 0)
			return 0;
		return (complite * 100) / duration;
	}
}
