import 'dart:math';

class CalculatorBrain {
  CalculatorBrain({
    this.height,
    this.weight,
  });
  final int? height;
  final int? weight;

  double? _bmi;

  String calcBMI() {
   _bmi = weight! / pow(height! / 100, 2);
    return _bmi!.toStringAsFixed(1);
  }

  String getResult() {
    if (_bmi! >= 25) {
      return 'Overeight';
    } else if (_bmi! > 18.5) {
      return 'Normal';
    } else {
      return 'Underweight';
    }
  }

getInterpretation(){
   if (_bmi! >= 25) {
      return 'You have a higher than normal body weight. But still you are look cute. May be You can workout.';
    } else if (_bmi! > 18.5) {
      return 'You have a normal body weight. Thats good. Keep it up';
    } else {
      return 'You have a lower than normal body weight. Stay safe during wind 😁. By the way try eating more. ';
    }
}
 

}
