# EthValidator

This console utility performs validation of private keys against its addresses.

# Motivation

Bug in JavaScript library ethereumjs-utils (< 2.2.3) used by many projects (for example MyEtherWallet)
cost many people lost coins:

https://www.reddit.com/r/ethereum/comments/47nkoi/psa_check_your_ethaddressorg_wallets_and_any/
https://www.reddit.com/r/ethereum/comments/48rt6n/using_myetherwalletcom_just_burned_me_for/

This program was created to defend against this kind of bugs.
To prove correctness of the addresses it uses completely different cryptographic libraries (Java implementation instead of C libraries used by JavaScript libraries and many other technologies).

# Usage

Check `examples/*.json` files for correct input format. It is consistent with VanityEth.

## Requirements

- JRE 1.8

## Examples

```bash
java -jar ethvalidator.jar examples/correct.json
Processed 3 entries
```

```bash
java -jar ethvalidator.jar examples/incorrect.json
Invalid address, expected 0xc775FC9feadE71B9E39fAEE43e15e64461d7c206, instead got 0xdeadbeefeadE71B9E39fAEE43e15e64461d7c206
Processed 3 entries
```

# Building

## Requirements

- JDK 1.8
- Gradle 3 (or newer)

## Steps

### Building

```bash
gradle build
```

### Running tests

```bash
gradle test
```

### Creating fat JAR

It creates JAR bundle as a single file at `./build/libs/ethvalidator-1.0-SNAPSHOT-all.jar`

```bash
gradle shadowJar
```
