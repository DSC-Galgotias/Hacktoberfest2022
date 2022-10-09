import 'package:bmi_calculator/constants.dart';
import 'package:flutter/material.dart';




class IconContent extends StatelessWidget {
  IconContent({
    @required this.icon,
    @required this.label,
    Key? key,
  }) : super(key: key);

  final IconData? icon;
  final String? label;

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Icon(
          icon,
          size: 80,
        ),
        SizedBox(
          height: 15,
        ),
        Text(
          label!,
          style: kLabelTextStyle
        ),
      ],
    );
  }
}
