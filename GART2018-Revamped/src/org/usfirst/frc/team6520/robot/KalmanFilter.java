package org.usfirst.frc.team6520.robot;

public class KalmanFilter {
	protected double err_measure;
	protected double err_estimate;
	protected double q;
	protected double current_estimate;
	protected double last_estimate;
	protected double kalman_gain;

	public KalmanFilter(double mea_e, double est_e, double q) {
		this.err_estimate = est_e;
		this.err_measure = mea_e;
		this.q = q;
	}

	public double updateEstimate(double mea) {
		kalman_gain = err_estimate / (err_estimate + err_measure);
		current_estimate = last_estimate + kalman_gain * (mea - last_estimate);
		err_estimate = (1.0 - kalman_gain) * err_estimate + Math.abs(last_estimate - current_estimate) * q;
		last_estimate = current_estimate;

		return current_estimate;
	}

	public void setMeasurementError(double mea_e) {
		this.err_measure = mea_e;
	}

	public void setEstimateError(double est_e) {
		this.err_estimate = est_e;
	}

	public void setProcessNoise(double q) {
		this.q = q;
	}

	public double getKalmanGain() {
		return this.kalman_gain;
	}
}
