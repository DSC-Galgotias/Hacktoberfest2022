import 'package:flutter/material.dart';


class RoundIconButton extends StatelessWidget {
  RoundIconButton({
    this.icon,
    @required this.onPressed,
    Key? key,
  }) : super(key: key);
  final IconData? icon;
  final Function()? onPressed;
  @override
  Widget build(BuildContext context) {
    return RawMaterialButton(
      child: Icon(icon),
      elevation: 6.0,
      constraints: BoxConstraints.tightFor(
        width: 56.0,
        height: 56.0,
      ),
      shape: CircleBorder(),
      fillColor: Color(0xFF4C4D5E),
      onPressed: onPressed,
    );
  }
}
